#*******************************************************************************
# Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
# All rights reserved. This program and the accompanying materials
# are made available under the terms of the Eclipse Public License v1.0
# which accompanies this distribution, and is available at
# http://www.eclipse.org/legal/epl-v10.html
#
# Contributors:
#   Benedek Izso - initial API and implementation
#   Gabor Szarnyas - initial API and implementation
#*******************************************************************************
#*******************************************************************************
# /*******************************************************************************
#  * Copyright (c) 2010-2015, Benedek Izso, Gabor Szarnyas, Istvan Rath and Daniel Varro
#  * All rights reserved. This program and the accompanying materials
#  * are made available under the terms of the Eclipse Public License v1.0
#  * which accompanies this distribution, and is available at
#  * http://www.eclipse.org/legal/epl-v10.html
#  *
#  * Contributors:
#  *   Benedek Izso - initial API and implementation
#  *   Gabor Szarnyas - initial API and implementation
#  *******************************************************************************/		
#*******************************************************************************
#!/bin/bash

# grep -v mysqld_safe filters the "/bin/sh /usr/bin/mysqld_safe" process
cat /proc/`ps auxw | grep '[m]ysqld' | grep -v mysqld_safe | awk '{ print $2; }'`/status | grep VmRSS | awk '{ print $2; }'
