'''
    2020. 07. 28
'''
class WebInfo:
    '''
    Web 페이지 정보를 가지고 있습니다.
    '''
    def __init__(self):
        self.__url = ""
        self.__params = ""

    def __str__(self):
        return f'str : {self.__url} - {self.__params}'

    def __repr__(self):
        return f'repr : {self.__url} - {self.__params}'

    def set_url(self, url):
        '''
        url 세팅
        '''
        self.__url = url

    def get_url(self):
        '''
        URL 반환
        '''
        return self.__url

    def set_param(self, params):
        '''
        파라미터 세팅
        '''
        self.__params = params

    def get_param_value(self, value):
        '''
        파라미터 값 반환
        '''
        return self.__params.get(value)
