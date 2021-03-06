/*
 ************************************************************************************
 * Copyright (C) 2001-2011 encuestame: open source social survey Copyright (C) 2009
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
dojo.provide("encuestame.org.core.shared.options.Scheduled");

dojo.require('encuestame.org.core.commons');
dojo.require('encuestame.org.core.shared.options.DateToClose');

/**
 * Represent a option to set a date to close anything.
 */
dojo.declare(
    "encuestame.org.core.shared.options.Scheduled",
    [dijit._Widget,
     dijit._Templated,
     encuestame.org.core.shared.utils.ContextSupport,
     encuestame.org.core.shared.utils.PublishSupport],{

     default_time : "T15:00:00",

     label : "Schedule this tweetPoll "
});
