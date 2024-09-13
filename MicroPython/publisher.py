import time
from umqtt.simple import MQTTClient

client_id = "pico_publisher"
broker = "192.168.1.134"  # Replace with the IP address of your desktop
topic = b"test/topic"

client = MQTTClient(client_id, broker)
print("Connecting to broker...")
client.connect()
print("Connected to broker")

def publish_message():
    while True:
        message = b"Hello from Pico!"
        print(f"Publishing: {message}")
        client.publish(topic, message)
        time.sleep(5)

try:
    publish_message()
finally:
    client.disconnect()
    print("Disconnected from broker")