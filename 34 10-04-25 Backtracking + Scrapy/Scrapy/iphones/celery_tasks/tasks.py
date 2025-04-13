from celery import Celery
import subprocess
import config

app = Celery('tasks', broker=config.REDIS_BROKER_URL)

@app.task
def run_all_spiders():
    subprocess.run(['scrapy', 'crawl', 'amazon'])
    subprocess.run(['scrapy', 'crawl', 'flipkart'])
