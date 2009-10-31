/**
 * encuestame: system online surveys Copyright (C) 2009 encuestame Development
 * Team
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of version 3 of the GNU General Public License as published by the
 * Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 59 Temple
 * Place, Suite 330, Boston, MA 02111-1307 USA
 */

package org.encuestame.core.persistence.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CatLocationType.
 *
 * @author Picado, Juan juan@encuestame.org
 * @since October 17, 2009
 */
@Entity
@Table(name = "cat_location_type")
public class CatLocationType implements Serializable {

    private String locIdType;
    private String description;
    private Integer level;

    public CatLocationType() {
    }

    public CatLocationType(String locIdType) {
        this.locIdType = locIdType;
    }

    public CatLocationType(String locIdType, String description, Integer level) {
        this.locIdType = locIdType;
        this.description = description;
        this.level = level;
    }

    @Id
    @Column(name = "loc_id_type", unique = true, nullable = false, length = 10)
    public String getLocIdType() {
        return this.locIdType;
    }

    public void setLocIdType(String locIdType) {
        this.locIdType = locIdType;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "level")
    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}