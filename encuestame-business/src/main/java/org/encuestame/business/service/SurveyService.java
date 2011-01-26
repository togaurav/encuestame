/*
 ************************************************************************************
 * Copyright (C) 2001-2009 encuestame: system online surveys Copyright (C) 2009
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.encuestame.business.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.encuestame.business.service.imp.ISurveyService;
import org.encuestame.core.util.ConvertDomainBean;
import org.encuestame.core.util.MD5Utils;
import org.encuestame.persistence.domain.Question;
import org.encuestame.persistence.domain.survey.QuestionPattern;
import org.encuestame.persistence.domain.survey.QuestionAnswer;
import org.encuestame.persistence.domain.survey.SurveyFolder;
import org.encuestame.persistence.domain.survey.SurveySection;
import org.encuestame.persistence.domain.survey.Survey;
import org.encuestame.persistence.exception.EnMeDomainNotFoundException;
import org.encuestame.persistence.exception.EnMeExpcetion;
import org.encuestame.utils.web.UnitAnswersBean;
import org.encuestame.utils.web.UnitFolder;
import org.encuestame.utils.web.UnitPatternBean;
import org.encuestame.utils.web.UnitQuestionBean;
import org.encuestame.utils.web.UnitSurvey;
import org.encuestame.utils.web.UnitSurveySection;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import twitter4j.TwitterException;
import twitter4j.http.RequestToken;

/**
 * Survey Service.
 * @author Picado, Juan juan@encuestame.org
 * @since 27/04/2009
 * @version $Id$
 */
@Service
public class SurveyService extends AbstractSurveyService implements ISurveyService {

    private Log log = LogFactory.getLog(this.getClass());

    private static Integer RANDOM_QUESTION_KEY = 500;

    /**
     * Create Question.
     * @param questionBean {@link UnitQuestionBean}.
     * @throws EnMeExpcetion exception
     */
    public Question createQuestion(final UnitQuestionBean questionBean) throws EnMeExpcetion{
            final Question question = new Question();
            try{

                question.setQuestion(questionBean.getQuestionName());
                question.setAccountQuestion(getAccountDao().getUserById(questionBean.getUserId()));
                question.setQidKey(MD5Utils.md5(RandomStringUtils.randomAlphanumeric(SurveyService.RANDOM_QUESTION_KEY)));
                question.setSharedQuestion(false);
                //save question
                getQuestionDao().saveOrUpdate(question);
                //questionBean.setId(question.getQid());
                for (final UnitAnswersBean answerBean : questionBean.getListAnswers()) {
                    this.saveAnswer(answerBean, question);
                }

            }
            catch (Exception e) {
                log.error("Error Creating Question "+e.getMessage());
                throw new EnMeExpcetion(e);
            }
            return question;
    }

    /**
     * Save Question Answer.
     * @param answerBean answer
     * @param question question
     */
    public void saveAnswer(final UnitAnswersBean answerBean, final Question question){
            final QuestionAnswer answer = new QuestionAnswer();
            answer.setQuestions(question);
            answer.setAnswer(answerBean.getAnswers());
            answer.setUrlAnswer(answerBean.getUrl());
            answer.setUniqueAnserHash(answerBean.getAnswerHash());
            this.getQuestionDao().saveOrUpdate(answer);
            answerBean.setAnswerId(answer.getQuestionAnswerId());
    }

    /**
     * Retrieve Answer By Question Id.
     * @param questionId question Id
     * @return List of Answers
     */
    public List<UnitAnswersBean> retrieveAnswerByQuestionId(final Long questionId){
        final List<QuestionAnswer> answers = this.getQuestionDao().getAnswersByQuestionId(questionId);
        final List<UnitAnswersBean> answersBean = new ArrayList<UnitAnswersBean>();
        for (QuestionAnswer questionsAnswers : answers) {
            answersBean.add(ConvertDomainBean.convertAnswerToBean(questionsAnswers));
        }
        return answersBean;
    }



    /**
     * Update Answer Name by Answer Id.
     * @param answerId answer Id
     * @param nameUpdated new name for answer
     * @throws EnMeExpcetion exception
     */
    public void updateAnswerByAnswerId(final Long answerId, String nameUpdated) throws EnMeExpcetion{
            final QuestionAnswer answer = getQuestionDao().retrieveAnswerById(answerId);
            if(answer==null){
                throw new EnMeExpcetion("answer not found");
            }
            answer.setAnswer(nameUpdated);
            getQuestionDao().saveOrUpdate(answer);
    }


    /**
     * Get Twitter Token.
     * @param consumerKey consumer key
     * @param consumerSecret consumer secret
     * @return {@link RequestToken}
     * @throws TwitterException exception
     */
    public RequestToken getTwitterToken(final String consumerKey,  final String consumerSecret) throws TwitterException{
            log.debug("getTwitterToken");
            return getTwitterService().getTwitterPing(consumerKey, consumerSecret);
    }


    /**
     * Load all questions.
     * @return List of {@link UnitQuestionBean}
     * @throws EnMeExpcetion exception
     */
    public List<UnitQuestionBean> loadAllQuestions() throws EnMeExpcetion {
        final List<UnitQuestionBean> listQuestionBean = new LinkedList<UnitQuestionBean>();
        try {
            final  List<Question> questionsList = getQuestionDao()
                    .loadAllQuestions();
            if (questionsList.size() > 0) {

               for (Question questions : questionsList) {
                    final UnitQuestionBean q = new UnitQuestionBean();
                    q.setId(Long.valueOf(questions.getQid().toString()));
                    q.setQuestionName(questions.getQuestion());
                    listQuestionBean.add(q);
                }
            }
        } catch (HibernateException e) {
            throw new EnMeExpcetion(e);
        } catch (Exception e) {
            throw new EnMeExpcetion(e);
        }
        return  listQuestionBean;
    }

    /**
     * Load pattern info.
     * @param unitPatternBean {@link UnitPatternBean}
     * @return {@link UnitPatternBean}
     * @throws EnMeExpcetion exception
     */
    public UnitPatternBean loadPatternInfo(UnitPatternBean unitPatternBean)
            throws EnMeExpcetion {
        if (unitPatternBean != null && unitPatternBean.getId() != null) {
            final QuestionPattern questionPatternDomain = getQuestionDao().loadPatternInfo(
                    unitPatternBean.getId());

            unitPatternBean.setId(questionPatternDomain.getPatternId());

            unitPatternBean.setDescripcion(questionPatternDomain.getDesQid());
            unitPatternBean.setLabel(questionPatternDomain.getLabelQid());
            unitPatternBean.setPatronType(questionPatternDomain.getPatternType());
            unitPatternBean.setTemplate(questionPatternDomain.getPatternTemplate());
            unitPatternBean.setClasspattern("classpatthern");
            unitPatternBean.setLevelpattern("2");
            unitPatternBean.setFinallity("save");
            //TODO : need more properties.
            return unitPatternBean;
        }
        else {
            throw new EnMeExpcetion("unit patter bean is null");
        }
    }

    /**
     * Load all Patrons.
     * @return List of {@link UnitPatternBean}
     * @throws EnMeExpcetion exception
     */
    public Collection<UnitPatternBean> loadAllPatrons()
            throws EnMeExpcetion {
        final List<UnitPatternBean> listPatronBean = new LinkedList<UnitPatternBean>();
        try {
            final List<QuestionPattern> patronList = getQuestionDao()
                    .loadAllQuestionPattern();
            if (patronList.size() > 0) {
               for (QuestionPattern patron : patronList) {
                    UnitPatternBean p = new UnitPatternBean();
                    p.setId(patron.getPatternId());
                    p.setPatronType(patron.getPatternType());
                    listPatronBean.add(p);
                }
            }
        } catch (HibernateException e) {
            throw new EnMeExpcetion(e);
        } catch (Exception e) {
            throw new EnMeExpcetion(e);
        }
        return listPatronBean;
    }

    /**
     * @param rANDOMQUESTIONKEY the rANDOM_QUESTION_KEY to set
     */
    public void setRandomQuestionKey(Integer rInteger){
        RANDOM_QUESTION_KEY = rInteger;
    }

    /**
     * Create Survey.
     * @param surveyBean
     * @throws EnMeExpcetion
     */
    public void createSurvey(final UnitSurvey surveyBean) throws EnMeExpcetion{
        try {
            final Survey surveyDomain = new Survey();
            surveyDomain.setTicket(surveyBean.getTicket());
            surveyDomain.setStartDate(surveyBean.getStartDate());
            surveyDomain.setEndDate(surveyBean.getEndDate());
            surveyDomain.setDateInterview(surveyBean.getDateInterview());
            surveyDomain.setComplete(surveyBean.getComplete());
            surveyDomain.setCustomMessage(surveyBean.getCustomMessage());
            surveyDomain.setCustomStartMessages(surveyBean.getCustomStartMessages());
            surveyDomain.setShowProgressBar(surveyBean.getShowProgressBar());
            surveyDomain.setOptionalTitle(surveyBean.getOptionalTitle());
            surveyDomain.setPassProtection(surveyBean.getPassProtection());
            surveyDomain.setIpProtection(surveyBean.getIpProtection());
            surveyDomain.setIpRestriction(surveyBean.getIpRestriction());
            surveyDomain.setPasswordRestrictions(surveyBean.getPasswordRestrictions());
            surveyDomain.setCloseAfterDate(surveyBean.getCloseAfterDate());
            surveyDomain.setCloseAfterquota(surveyBean.getCloseAfterquota());
            surveyDomain.setCloseAfterquota(surveyBean.getCloseAfterquota());
            surveyDomain.setClosedQuota(surveyBean.getClosedQuota());
            surveyDomain.setShowResults(surveyBean.getShowResults());
            surveyDomain.setNumbervotes(surveyBean.getNumbervotes());
            surveyDomain.setHits(surveyBean.getHits());
            surveyDomain.setAdditionalInfo(surveyBean.getAdditionalInfo());
            surveyDomain.setShowAdditionalInfo(surveyBean.getShowAdditionalInfo());
            surveyDomain.setNotifications(surveyBean.getNotifications());
            surveyDomain.setName(surveyBean.getName());
            getSurveyDaoImp().saveOrUpdate(surveyDomain);
            surveyBean.setSid(surveyBean.getSid());
        } catch (Exception e) {
             log.error("Error Creating Survey "+e.getMessage());
             throw new EnMeExpcetion(e);
        }
    }


    /**
     * Create Survey Section.
     * @param surveySectionBean
     */
    public void createSurveySection(final UnitSurveySection surveySectionBean){
        try {
            final SurveySection surveySectionDomain = new SurveySection();
            surveySectionDomain.setDescSection(surveySectionBean.getName());
            for (final UnitQuestionBean questionBean : surveySectionBean.getListQuestions()) {
                this.saveQuestions(questionBean);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    /**
     * Save Questions.
     * @param questionBean
     */
    public void saveQuestions(final UnitQuestionBean questionBean){
        final Question question = new Question();
        question.setQuestion(questionBean.getQuestionName());
        //	question.setQidKey();
        question.setQuestionPattern(question.getQuestionPattern());
        question.setQuestionsAnswers(question.getQuestionsAnswers());
        question.setAccountQuestion(getAccountDao().getUserById(questionBean.getUserId()));
       // question.setSharedQuestion();
        this.getQuestionDao().saveOrUpdate(question);
        questionBean.setId(question.getQid());
}

    /**
     * Create Survey Folder.
     * @param folderName
     * @param username
     * @return
     * @throws EnMeDomainNotFoundException
     */
    public UnitFolder createSurveyFolder(final String folderName, final String username)
            throws EnMeDomainNotFoundException{
        final SurveyFolder surveyFolderDomain = new SurveyFolder();
        surveyFolderDomain.setUsers(getUserAccount(username).getAccount());
        surveyFolderDomain.setCreatedAt(new Date());
        surveyFolderDomain.setFolderName(folderName);
        this.getSurveyDaoImp().saveOrUpdate(surveyFolderDomain);
        return ConvertDomainBean.convertFolderToBeanFolder(surveyFolderDomain);
       }

    /**
     * Update Survey Folder.
     * @param folderId
     * @param folderName
     * @param username
     * @return
     * @throws EnMeDomainNotFoundException
     */
    public UnitFolder updateSurveyFolder(final Long folderId, final String folderName, final String username)
                throws EnMeDomainNotFoundException{
        final SurveyFolder surveyPollFolder = this.getSurveyFolder(folderId);
        if(surveyPollFolder == null) {
            throw new EnMeDomainNotFoundException("Survey Poll Folder not found");
        }
        else{
            surveyPollFolder.setFolderName(folderName);
            getSurveyDaoImp().saveOrUpdate(surveyPollFolder);
        }
         return ConvertDomainBean.convertFolderToBeanFolder(surveyPollFolder);
     }

    /**
     * Get Survey Folder.
     * @param folderId
     * @return
     */
    private SurveyFolder getSurveyFolder(final Long folderId){
        return this.getSurveyDaoImp().getSurveyFolderById(folderId);
    }

    /**
     * Delete Survey Folder.
     * @param folderId
     * @throws EnMeDomainNotFoundException
     */
    public void deleteSurveyFolder(final Long folderId) throws EnMeDomainNotFoundException{
        final SurveyFolder surveyFolder = this.getSurveyFolder(folderId);
        if(surveyFolder != null){
            getSurveyDaoImp().delete(surveyFolder);
        } else {
            throw new EnMeDomainNotFoundException("Survey folder not found");
        }
    }

    /**
     * Get Survey Folders by Folder Id and User.
     * @param folderId
     * @param userId
     * @return
     */

    private SurveyFolder getSurveysFolderByFolderIdandUser(final Long folderId, final Long userId){
        return this.getSurveyDaoImp().getSurveyFolderByIdandUser(folderId, userId);
   }

    /**
     * Add Survey to Folder.
     * @param folderId
     * @param username
     * @param surveyId
     * @throws EnMeDomainNotFoundException
     */
    public void addSurveyToFolder(final Long folderId, final String username, final Long surveyId) throws EnMeDomainNotFoundException{
        final SurveyFolder surveyFolder = this.getSurveysFolderByFolderIdandUser(folderId, getPrimaryUser(username));
        if(surveyFolder!=null) {
            final Survey survey = getSurveyDaoImp().getSurveyByIdandUserId(surveyId, getPrimaryUser(username));
            survey.setSurveysfolder(surveyFolder);
            getSurveyDaoImp().saveOrUpdate(survey);
            } else {
            throw new EnMeDomainNotFoundException("Survey folder not found");
        }
   }

}
