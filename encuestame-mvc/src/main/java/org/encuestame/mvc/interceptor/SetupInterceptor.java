/*
 ************************************************************************************
 * Copyright (C) 2001-2011 encuestame: system online surveys Copyright (C) 2011
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.encuestame.mvc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.encuestame.business.setup.StartupProcess;
import org.encuestame.core.config.EnMePlaceHolderConfigurer;
import org.encuestame.core.files.PathUtil;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

/**
 * Setup interceptor.
 * @author Picado, Juan juanATencuestame.org
 * @since Mar 25, 2011
 */
public class SetupInterceptor extends AbstractEnMeInterceptor{

    private Logger log = Logger.getLogger(this.getClass());

    //@Autowired
    public StartupProcess startup;

    @Override
    public void afterCompletion(HttpServletRequest arg0,
            HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        log.debug("SetupInterceptor afterCompletion");
        log.info(this.startup);
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2, ModelAndView arg3) throws Exception {
         log.debug("SetupInterceptor postHandle");
         log.info(this.startup);

    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
            Object arg2) throws Exception {
        final String context = arg0.getContextPath();
        final StringBuilder path = new StringBuilder(context);
        path.append(PathUtil.setup);
        log.debug("Request URI "+arg0.getRequestURI());
        log.debug("Context Path URI "+path.toString());
        log.debug("Context Path URI "+ (arg0.getRequestURI().equalsIgnoreCase(path.toString())));
        log.debug("Context Path URI "+ (arg0.getRequestURI().equals(path.toString())));
        if (!arg0.getRequestURI().toString().equals(path.toString())) {
            final String uuid = EnMePlaceHolderConfigurer.getConfigurationManager().getProperty("install.uuid");
            log.debug("EnMePlaceHolderConfigurer.getConfigurationManager() -> " +
            EnMePlaceHolderConfigurer.getConfigurationManager().getXmlConfiguration().getFileName());
            log.debug("intalled.uuid:->"+uuid);
            if (uuid == null || uuid == "") {
                     log.debug("system not installed ...");
                     final ModelAndView modelAndView = new ModelAndView("redirect:/setup");
                     throw new ModelAndViewDefiningException(modelAndView);
            } else {
                log.info("Encuestame Install Info:");
                log.info("Version:			");
                log.info("Last Update:		");
                log.info("Tables:		");
            }

        } else {
            log.debug("you are on setup interface ...");
        }

        log.debug("SetupInterceptor preHandle");
        return true;
    }

    /**
     * @return the startup
     */
    public StartupProcess getStartup() {
        return startup;
    }

    /**
     * @param startup the startup to set
     */
    public void setStartup(StartupProcess startup) {
        this.startup = startup;
    }



}
