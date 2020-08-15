'''''
    2020. 08. 04
'''''

from bs4 import BeautifulSoup
import requests
from web_info import WebInfo

def crawling(search_text):
    '''
    crawling 테스트 함수
    '''
    naver_info = WebInfo()
    url = 'https://search.naver.com/search.naver?where=news&sm=tab_jum&query='
    full_url = url + str(search_text)
    naver_info.set_url(full_url)
    source_code = requests.get(naver_info.get_url(), headers={'User-Agent':'Mozilla/5.0'})
    plain_text = source_code.text
    html = BeautifulSoup(plain_text, 'lxml')
    print(html)


crawling("정보보안")
