# Use official Python image

FROM python:3.9

# Set working directory

WORKDIR /app

# Copy application files

COPY app.py /app/

# Install dependencies

RUN pip install flask

# Create a log directory

RUN mkdir -p /logs && chmod 777 /logs

# Expose application port

EXPOSE 80

# Run Flask application

CMD ["python", "app.py"]