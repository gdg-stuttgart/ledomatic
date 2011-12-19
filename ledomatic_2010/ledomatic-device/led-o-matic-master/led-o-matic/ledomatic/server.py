# -*- coding: utf-8 -*-

import urllib
from restful_lib import Connection

class Server:

    def __init__(self, root_url="http://led-o-matic.appspot.com"):
    	self.root_url = root_url
    	self.conn = Connection(self.root_url)
    	self.name = ""

    def getPinStatus(self, pins_name, pin_id):
        request = self.name + '/' + pins_name + '/' + pin_id
        response = self.conn.request_get(request)
        return response['body']
  

    def login(self, name):
		self.name = name
		response = self.conn.request_post('/' + name)
		return self.root_url + '/' + self.name + response['body']
		
		
	
	