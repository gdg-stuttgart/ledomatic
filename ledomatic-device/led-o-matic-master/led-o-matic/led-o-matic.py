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

#serv = Server("http://localhost:8080")
serv = Server()
print "connected to " + str(serv.login('rr/L1'))


def setMode(a, pin_nr, type):
  a.pin_mode(pin_nr, type)
  #a.delay(1)

def setOut(a, out_nr, value):
    a.digital_write(out_nr, value)
    #a.delay(1)
    
def setRGBOut(a, red, green, blue):
    a.analog_write(3, blue)
    a.analog_write(5, green)
    a.analog_write(6, red)
    #a.delay(1)    
    
def get_rgb_from_hex(hex_color):
    print hex_color
    value_color = int(hex_color, 16)
    red = 255 - ((value_color >> 16) & 0xFF)
    gred = 255 - ((value_color >> 8) & 0xFF)
    blue = 255 - value_color & 0xFF
    return red, gred, blue

# arduino on mac on the port with default 115200
a = Arduino('/dev/tty.usbmodem411')
setMode(a, 13, firmata.OUTPUT)
setMode(a, 3, firmata.PWM)
setMode(a, 5, firmata.PWM)
setMode(a, 6, firmata.PWM)
last_r = last_g = last_b = 0;

# infinite loop, Arduino instance should runs
while True:
  """a.parse()
  # check LED status
  answer = serv.getPinStatus('OUT','13')
  print answer
  if answer == 'result=Off':
    setOut(a, 13, firmata.LOW)
  else:
  	setOut(a,13, firmata.HIGH)
  """
  answer = serv.getPinStatus('RGB','0')
  print answer
  if len(answer) == len('result=xxxxxx'):
      #parse RGB from string
      
      key_value_lst = answer.split('=')
      print key_value_lst 
      r,g,b = get_rgb_from_hex(key_value_lst[1])
      setRGBOut(a, r, g, b)
  # wait
  a.delay(1)
 