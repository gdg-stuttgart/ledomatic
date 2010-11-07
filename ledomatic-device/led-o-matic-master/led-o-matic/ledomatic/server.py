# -*- coding: utf-8 -*-

import urllib
from restful_lib import Connection

class Server:

    def __init__(self, root_url="http://led-o-matic"):
	    self.root_url = root_url
	    self.conn = Connection(self.root_url)
	    self.name = ""

    def login(self, name):
		response = self.conn.request_put('/' + name)
		self.name = name
		print response
		
	def getStatus(self, name):
		response = self.conn.request_get('/' + self.name)
		print response
