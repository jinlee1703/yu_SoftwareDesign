import time
import re
import requests
from bs4 import BeautifulSoup
from wordcloud import WordCloud
import matplotlib.pyplot as plt

# https://codereview.stackexchange.com/questions/208277/web-scraping-google-trends-in-python
def fetch_xml(country_code):
    url = f"https://trends.google.com/trends/trendingsearches/daily/rss?geo={country_code}"
    start = time.time()
    response = requests.get(url)
    response_time = time.time() - start
    print(f"The request took {response_time}s to complete.")
    return response.content

def trends_retriever(country_code):
    xml_document = fetch_xml(country_code)
    soup = BeautifulSoup(xml_document, "lxml")
    titles = soup.find_all("title")
    approximate_traffic = soup.find_all("ht:approx_traffic")
    return {title.text: re.sub("[+,]", "", traffic.text)
            for title, traffic in zip(titles[1:],  approximate_traffic)}


if __name__ == '__main__':
    trends = trends_retriever("KR")
    print(trends)

    word_count = {}

    for i in trends:
        word_count[i] = int(trends.get(i))

    wc = WordCloud(font_path='malgun', background_color='ivory', width=800, height=600)
    cloud = wc.generate_from_frequencies(word_count)
    plt.figure(figsize=(8,8))
    plt.imshow(cloud)
    plt.axis('off')
    plt.show()