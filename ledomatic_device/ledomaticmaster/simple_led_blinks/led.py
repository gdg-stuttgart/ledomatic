#! /usr/bin/env python
"""
Simple LED blinking example using Python Firmata
Copyright (C) 2008  laboratorio (info@laboratorio.us)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
"""

from firmata import * 

a = Arduino('/dev/tty.usbmodem411')
a.pin_mode(13, firmata.OUTPUT)
a.delay(2)

while True:
    a.digital_write(13, firmata.HIGH)
    a.delay(2)
    a.digital_write(13, firmata.LOW)
    a.delay(2)