import pytest
import conftest
from pages.login import Login_page
from playwright.sync_api import expect



@pytest.mark.parametrize("username,password,expected", [
    
    ("Admin", "wrong", False),
    ("wrong", "admin123", False),
    ("", "admin123", False),
    ("Admin", "", False),
    ("Admin", "admin123", True)
])
def test_login(page,username,password,expected):
    login_page = Login_page(page)
    login_page.login(username, password)

    if expected:
        expect(page).to_have_title("OrangeHRM")
    else:
        expect(page.locator(".oxd-alert-content-text")).to_have_text("Invalid credentials")
    


