/*
 *  Copyright 2013~2014 Dan Haywood
 *
 *  Licensed under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.isisaddons.module.settings.dom;

import org.apache.isis.applib.DomainObjectContainer;
import org.apache.isis.applib.security.UserMemento;



public interface UserSetting extends Setting {

    String getKey();
    
    /**
     * Typically as obtained from the {@link UserMemento#getName() UserMemento} class
     * (accessible in turn from the {@link DomainObjectContainer#getUser() DomainObjectContainer}).
     */
    String getUser();
    
}