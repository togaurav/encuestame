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
package org.encuestame.core.persistence.pojos;

import org.encuestame.core.persistence.pojo.CatLocation;
import org.encuestame.test.config.AbstractBaseTest;
import org.junit.Test;

/**
 * Test Catalog Location.
 *
 * @author Morales Urbina, Diana paola@encuestame.org
 * @since 02/11/2009 16:18:49
 */
public class TestCatLocation extends AbstractBaseTest{
    /**
     * Test Catalag Location.
     */
    @Test
    public void testCatLocation(){
    final CatLocation catLoc = new CatLocation();
    catLoc.setActive("S");
    catLoc.setDescription("Locate Description");
    catLoc.setLevel(1);
    catLoc.setLat(2F);
    catLoc.setLng(3F);
    catLoc.setTidtype("1");
    getCatLocationDao().saveOrUpdate(catLoc);
     //catLoc.setLocateId(locateId);
    }

    /**
     * Test Catalog Location Constructor.
     */
    @Test
    public void testCatLocationConstructor(){
       new CatLocation(1L,"1","Locate Description",3);
       new CatLocation(1L,"1","Locate Description",1,"S",2.5F,22.5F);
    }

}