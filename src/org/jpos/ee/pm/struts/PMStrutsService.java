/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2010 Alejandro P. Revilla
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jpos.ee.pm.struts;

/**This is an implementation of the PMService for struts.*/
import org.jpos.ee.Constants;
import org.jpos.ee.pm.core.PMService;
import org.jpos.ee.pm.struts.converter.DefaultStrutsConverter;

/**
 * QBean for struts Presentation Manager service
 * 
 * @author jpaoletti
 */
public class PMStrutsService extends PMService implements Constants {

    @Override
    protected void initService() throws Exception {
        super.initService();
        if (getDefaultConverter() == null) {
            setDefaultConverter(new DefaultStrutsConverter());
        }
    }
}
