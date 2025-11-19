import scrapy
from bookstore.items import BookItem

class BooksSpider(scrapy.Spider):
    name = 'books_spider'
    start_urls = ['https://books.toscrape.com/']

    def parse(self, response):
        books = response.css('article.product_pod')
        for book in books:
            item = BookItem()
            item['title'] = book.css('h3 a::attr(title)').get()
            item['price'] = book.css('p.price_color::text').get()
            item['availability'] = book.css('p.instock.availability::text').get()
            item['rating'] = book.css('p.star-rating::attr(class)').get().split(' ')[1] 

            yield item

        next_page = response.css('li.next a::attr(href)').get()
        if next_page:
            yield response.follow(next_page, callback=self.parse)
