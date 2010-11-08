# The MIT License
# 
# Copyright (c) 2008 William T. Katz
# 
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to 
# deal in the Software without restriction, including without limitation 
# the rights to use, copy, modify, merge, publish, distribute, sublicense, 
# and/or sell copies of the Software, and to permit persons to whom the 
# Software is furnished to do so, subject to the following conditions:
# 
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
# 
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
# FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER 
# DEALINGS IN THE SOFTWARE.

__author__ = 'Michal Harakal'

import datetime
import string
import re
import os
import cgi
import urllib

import logging

from google.appengine.ext import webapp
from google.appengine.api import users
from google.appengine.ext import db
from google.appengine.ext.webapp import template
from google.appengine.api import mail
from google.appengine.api import urlfetch

from handlers import restful
from models import ledomatic
    
class RootHandler(restful.Controller):
    def get(self):
        logging.info("RootHandler#MISKO")
        # get list of devices connected
        query = db.Query(ledomatic.Device)
        results = query.fetch(limit=5)
        dev_lst=[]
        for device in results:
          dev_lst.append(device.name)
        restful.send_successful_response(self, "result=" + ",".join(dev_lst))

    def post(self, device_name):
        logging.info("RootHandler#(%s)", device_name)
        device = ledomatic.Device.get_by_key_name(device)
        if not device:
          device = ledomatic.Device()
          device.name = device_name
          device.put()
        # login device into list of device, if OK get the name back
        restful.send_successful_response(self, 'L1')
        
class DevicesHandler(restful.Controller):
    def get(self, *path):
        logging.info(path)
        # we pretends L1 is conencted
        restful.send_successful_response(self, 'L1')

    def post(self, device_name):
        logging.debug("DevicesHandler#(%s)", device_name)
        query = ledomatic.Device.all()
        query.filter('name =', device_name)
        devices = query.fetch(limit=5)
        if not devices:
          device = ledomatic.Device()
          device.name = device_name
          device.put()
        # login device into list of device, if OK get the name back
        restful.send_successful_response(self, device_name)


class DeviceHandler(restful.Controller):
    def get(self, device, pin):
        logging.info(device + pin)
        # we pretends L1 is conencted
        restful.send_successful_response(self, 'L1')

    def post(self):
        logging.debug("RootHandler#post")
        # login device into list of device, if OK get the name back
        restful.send_successful_response(self, 'L1')
        
def getValueFromPin(device, pin_name, pin_id_str):
    if pin_name == 'OUT':
        dev_value_str = device.pins_OUT
    elif pin_name == 'IN':
        dev_value_str = device.pins_IN
    elif pin_name == 'RGB':
        dev_value_str = device.pins_RGB
    else:
        dev_value_str = ''
        
    if dev_value_str:    
        value_lst = dev_value_str.split(',')
    else:
      value_lst = []
       
    pin_id = int(pin_id_str)

    if len(value_lst) <= pin_id:            
        for i in range(0,pin_id + 1):
            value_lst.append('0')   

    return value_lst[pin_id]
    
def setValueToPin(device, pins_type_name, pin_id_str, value):
    if pins_type_name == 'OUT':
        pins_value_str = device.pins_OUT
    elif pins_type_name == 'IN':
        pins_value_str = device.pins_IN
    elif pins_type_name == 'RGB':
        pins_value_str = device.pins_RGB
    elif pins_type_name == 'AIN':
        pins_value_str = device.pins_AIN    
    elif pins_type_name == 'PWM':
        pins_value_str = device.pins_PWM
    else:
        pins_value_str = ''
        
    if  pins_value_str:   
        values_lst = pins_value_str.split(',')
    else:
        values_lst = []
        
    pin_id = int(pin_id_str)

    if len(values_lst) <= pin_id:            
        for i in range(0,pin_id + 1):
            values_lst.append('0')  

    values_lst[pin_id] = value
    pins_value_str = ','.join(values_lst)
    
    if pins_type_name == 'OUT': 
        device.pins_OUT = pins_value_str
    elif pins_type_name == 'IN': 
        device.pins_IN = pins_value_str
    elif pins_type_name == 'RGB':
        device.pins_RGB = pins_value_str
    elif pins_type_name == 'AIN':
        device.pins_AIN = pins_value_str
    elif pins_type_name == 'PWM':
        device.pins_PWM = pins_value_str    
    else:
        logging.info("Undowns pins name")
        
    device.put()    
 
class PinsHandler(restful.Controller):
    def get(self, device_name, pin_name, pin_id_str):
        logging.info("PinsHandler" + device_name + pin_name + pin_id_str)
        # we pretends L1 is conencted
        query = ledomatic.Device.all()
        query.filter('name =', device_name)
        device = query.fetch(limit=5)
        if device:
            if  getValueFromPin(device[0], pin_name, pin_id_str) == "1":
                restful.send_successful_response(self, 'result=On')
            else:
                restful.send_successful_response(self, 'result=Off')

    def post(self, device_name, pins_name, pin_id_str):
        logging.info("PinsHandler#post" + device_name + pins_name + pin_id_str)
        query = ledomatic.Device.all()
        query.filter('name =', device_name)
        device = query.fetch(limit=5)
        logging.info("PinsHandler#post#body" + self.request.body)
        if device:
            if self.request.body == 'state=On':
                setValueToPin(device[0], pins_name, pin_id_str, '1')
            else:
                setValueToPin(device[0], pins_name, pin_id_str, '0')
        
        restful.send_successful_response(self, '')

