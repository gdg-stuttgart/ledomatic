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
from ledomatic.server import Server

serv = Server()
serv.login('L1')


def setMode(a, pin_nr, type):
  a.pin_mode(pin_nr, type)
  a.delay(2)

def setOut(a, out_nr, value):
    a.digital_write(out_nr, value)
    a.delay(2)

# arduino on mac on the port with default 115200
a = Arduino('/dev/tty.usbmodem411')
setMode(a, 13, firmata.OUTPUT)

# infinite loop, Arduino instance shoul runs
while True:
  # check status
  answer = serv.getStatus()
  setOut(a, 13, answer.value)
  # wait
  a.delay(2)

