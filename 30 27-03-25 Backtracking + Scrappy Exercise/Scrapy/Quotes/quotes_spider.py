import scrapy
from quotes_scraper.items import QuoteItem

class QuotesSpider(scrapy.Spider):
    name = 'quotes'
    start_urls = ['http://quotes.toscrape.com']

    def parse(self, response):
        quotes = response.css('div.quote')
        for q in quotes:
            item = QuoteItem()
            item['text'] = q.css('span.text::text').get()
            item['author'] = q.css('small.author::text').get()
            item['tags'] = q.css('div.tags a.tag::text').getall()
            yield item

        next_page = response.css('li.next a::attr(href)').get()
        if next_page:
            yield response.follow(next_page, callback=self.parse)
