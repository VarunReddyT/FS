import time

LOG_FILE = "/logs/app.log"

def tail_logs():
    """ Continuously read and print logs from the log file. """
    try:
        with open(LOG_FILE, "r") as f:
            # Move to the end of the file
            f.seek(0, 2)

            while True:
                line = f.readline()
                if line:
                    print(line.strip())  # Print logs to stdout (Kubernetes will collect them)
                else:
                    time.sleep(1)  # Wait if no new logs

    except FileNotFoundError:
        print("Log file not found. Waiting for it to be created...")
        time.sleep(5)
        tail_logs()

if __name__ == "__main__":
    tail_logs()
    