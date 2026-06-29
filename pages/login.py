import pytest


class Login_page:

    input_email="input[name='username']"
    imput_pw="input[name='password']"
    sub_login="button[type='submit']"

    def __init__(self,page):
        self.page=page
        

    def login(self, unmae, pw):
        self.page.wait_for_timeout(2000)
        self.page.locator(self.input_email).fill(unmae)
        self.page.wait_for_timeout(2000)
        self.page.locator(self.imput_pw).fill(pw)
        self.page.wait_for_timeout(2000)
        self.page.locator(self.sub_login).click()
        self.page.wait_for_timeout(2000)
       
        

