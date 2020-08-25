'''''
    2020. 08. 04
    @author kmy
'''''

import collections
import requests
from bs4 import BeautifulSoup
from web_info import WebInfo


def crawling_relevance(search_text):
    '''
    naver news crawling 관련도순
    '''
    news_info = collections.defaultdict(dict)
    naver_info = WebInfo()
    for i in range(1, 100, 10):
        url = 'https://search.naver.com/search.naver?where=news&sort=0&sm=tab_jum&query='
        full_url = url + str(search_text)
        full_url = full_url + "&start="
        full_url = full_url + str(i)
        naver_info.set_url(full_url)
        source_code = requests.get(naver_info.get_url(), headers={'User-Agent':'Mozilla/5.0'})
        plain_text = source_code.text
        html = BeautifulSoup(plain_text, 'lxml')
        articles = html.select("ul.type01 > li")
        i = 0
        for article in articles:
            news_info[i]['title'] = article.select_one("a._sp_each_title").text
            news_info[i]['link'] = article.select_one("a._sp_each_title")['href']
            news_info[i]['source'] = article.select_one("span._sp_each_source").text
            i += 1

    # print(json.dumps(news_info, sort_keys=True, indent=4, ensure_ascii=False))
    return news_info

def crawling_latest(search_text):
    '''
    naver news crawling 최신순
    '''
    news_info = collections.defaultdict(dict)
    naver_info = WebInfo()
    for i in range(1, 100, 10):
        url = 'https://search.naver.com/search.naver?where=news&sm=tab_jum&sort=1&query='
        full_url = url + str(search_text)
        full_url = full_url + "&start="
        full_url = full_url + str(i)
        naver_info.set_url(full_url)
        source_code = requests.get(naver_info.get_url(), headers={'User-Agent':'Mozilla/5.0'})
        plain_text = source_code.text
        html = BeautifulSoup(plain_text, 'lxml')
        articles = html.select("ul.type01 > li")
        i = 0
        for article in articles:
            news_info[i]['title'] = article.select_one("a._sp_each_title").text
            news_info[i]['link'] = article.select_one("a._sp_each_title")['href']
            news_info[i]['source'] = article.select_one("span._sp_each_source").text
            i += 1
    return news_info

# crawling_relevance("정보보안")
