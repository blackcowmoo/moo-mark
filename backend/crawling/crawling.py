'''
    2020. 07. 28
'''

from bs4 import BeautifulSoup
import requests


class WebInfo:
    '''
    Web 페이지 정보를 가지고 있습니다.
    '''
    def __init__(self):
        self.__url = ""

    def get_url(self):
        '''
        URL 반환
        '''
        return self.__url

    def set_url(self, urlvalue):
        '''
        URL 세팅
        '''
        self.__url = urlvalue


def crawling(maxpage):
    '''
    crawling 테스트 함수
    '''
    page = 1
    while page < maxpage:
        url = 'http://creativeworks.tistory.com/' + str(page)
        source_code = requests.get(url)
        plain_text = source_code.text
        soup = BeautifulSoup(plain_text, 'lxml')
        print(soup)


crawling(2)
