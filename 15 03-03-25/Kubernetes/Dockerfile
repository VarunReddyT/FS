# Use an official Python runtime as a parent image
FROM python:3.9

# Set the working directory in the container
WORKDIR /app

# Copy the application files into the container
COPY app.py /app

# Install Flask
RUN pip install flask

# Expose the application port
EXPOSE 80

# Run the Python server
CMD ["python", "app.py"]