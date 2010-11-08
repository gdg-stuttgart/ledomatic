# -*- coding: utf-8 -*-

import urllib
from restful_lib import Connection

class Server:

    def __init__(self, root_url="http://led-o-matic.appspot.com"):
    	self.root_url = root_url
    	self.conn = Connection(self.root_url)
    	self.name = ""

    def getStatus(self):
        print "get" + '/rr/' + self.name + '/OUT/13'
        response = self.conn.request_get(self.name + '/OUT/13' )
        return response['body']

    def login(self, name):
		self.name = name
		response = self.conn.request_put('/' + name)
		
	
	