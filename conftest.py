import pytest
from playwright.sync_api import sync_playwright
from pages.login import Login_page

@pytest.fixture(scope="session")
def page():
    with sync_playwright() as p:
        browser = p.chromium.launch(headless=False)
        context = browser.new_context()
        page=context.new_page()
        page.goto("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
        yield page
        context.close()
        browser.close()
    