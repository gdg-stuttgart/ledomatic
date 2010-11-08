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
        restful.send_successful_response(self, results[0].name)

    def post(self, device_name):
        logging.debug("RootHandler#(%s)", device_name)
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

class PinsHandler(restful.Controller):
    def get(self, device, pin, pin_id):
        logging.info(device + pin + pin_id)
        # we pretends L1 is conencted
        restful.send_successful_response(self, 'L1')

    def post(self):
        logging.debug("RootHandler#post")
        # login device into list of device, if OK get the name back
        restful.send_successful_response(self, 'L1')

