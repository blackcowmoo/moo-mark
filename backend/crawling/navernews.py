'''''
    2020. 08. 04
    @author kmy
'''''

from bs4 import BeautifulSoup
import requests
from web_info import WebInfo

def crawling(search_text):
    '''
    naver news crawling
    '''

    naver_info = WebInfo()
    for i in range(1, 100, 10):
        url = 'https://search.naver.com/search.naver?where=news&sm=tab_jum&query='
        full_url = url + str(search_text)
        full_url = full_url + "start="
        full_url = full_url + str(i)
        naver_info.set_url(full_url)
        source_code = requests.get(naver_info.get_url(), headers={'User-Agent':'Mozilla/5.0'})
        plain_text = source_code.text
        html = BeautifulSoup(plain_text, 'lxml')
        articles = html.select("ul.type01 > li")

        for article in articles:
            title = article.select_one("a._sp_each_title").text
            source = article.select_one("span._sp_each_source").text
            print(title, source)

crawling("정보보안")
