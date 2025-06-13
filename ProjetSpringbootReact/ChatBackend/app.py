from flask import Flask, request, jsonify
from flask_cors import CORS
from ai_coder import chain

app = Flask(__name__)
CORS(app)  # Allow requests from your frontend

@app.route('/api/evaluate', methods=['POST'])
def evaluate():
    data = request.get_json()
    exercise_answer = data.get('exercise_answer', '')
    if not exercise_answer:
        return jsonify({'error': 'No exercise_answer provided'}), 400

    # Run the chain
    result = chain.run({'exercise_answer': exercise_answer})
    return jsonify({'result': result})

if __name__ == '__main__':
    app.run(debug=True)