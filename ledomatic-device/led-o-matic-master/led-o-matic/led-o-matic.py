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

serv = Server("http://localhost:8080")
print "connected to " + str(serv.login('rr/L1'))


def setMode(a, pin_nr, type):
  a.pin_mode(pin_nr, type)
  #a.delay(1)

def setOut(a, out_nr, value):
    a.digital_write(out_nr, value)
    #a.delay(1)
    
def setRGBOut(a, red, green, blue):
    a.analog_write(3, red)
    a.analog_write(6, green)
    a.analog_write(7, blue)
    #a.delay(1)    
    
def get_rgb_from_hex(hex_color):
    red = 255
    gred = 0
    blue = 0
    return red, gred, blue

# arduino on mac on the port with default 115200
a = Arduino('/dev/tty.usbmodem411')
setMode(a, 3, firmata.OUTPUT)
setMode(a, 5, firmata.OUTPUT)
setMode(a, 6, firmata.OUTPUT)

# infinite loop, Arduino instance should runs
while True:
  # check LED status
  answer = serv.getPinStatus('OUT','13')
  print answer
  if answer == 'result=Off':
    setOut(a, 13, firmata.LOW)
  else:
  	setOut(a,13, firmata.HIGH)

  answer = serv.getPinStatus('RGB','0')
  print answer
  if len(answer) == len('color=xxxxxx'):
    # parse RGB from string
    key_value_lst = '='.split(answer)
    setRGBOut(a, get_rgb_from_hex(key_value_lst[0]))
  # wait
  a.delay(1)
 