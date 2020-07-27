from urllib.request import urlopen
from bs4 import BeautifulSoup
import requests


def crawling(maxPage):
    page = 1
    while page < maxPage:
        url = 'http://creativeworks.tistory.com/' + str(page)
        source_code = requests.get(url)
        plain_text = source_code.text
        soup = BeautifulSoup(plain_text, 'lxml')
        print(soup)


crawling(2)
