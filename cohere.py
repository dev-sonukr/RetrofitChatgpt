from flask import Flask, jsonify, request
import cohere

app = Flask(__name__)

# Replace with your Cohere trial API key
cohere_api_key = "zKBIXZJCYZOdHJtew5jykdixnMddoD9BLuAfa0SK"
co = cohere.Client(cohere_api_key)

# Helper function to interact with Cohere
def chat_with_cohere(question):
    response = co.generate(
        model='command-xlarge-nightly',  # Use the latest model
        prompt=question,
        max_tokens=50,  # Limit the response length
        temperature=0.7,  # Adjust creativity (0.0 = deterministic, 1.0 = highly creative)
        )
    return response.generations[0].text.strip()
    

# Define API routes
@app.route('/api/hello', methods=['GET'])
def hello():
    # Example response
    answer = chat_with_cohere("What is 1 + 1?")
    return jsonify({'message': 'Hello, this is your REST API!', 'answer': answer})


app.run()
