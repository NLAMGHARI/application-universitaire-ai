# Importing the Ollama class for using a local language model (LLM)
from langchain.llms import Ollama

# Importing PromptTemplate to create structured prompts for the LLM
from langchain.prompts import PromptTemplate

# Importing LLMChain to link a prompt and an LLM together into a reusable chain
from langchain.chains import LLMChain


# Initialize the local LLM (Large Language Model) with the "deepseek-coder" model
llm = Ollama(model="mistral")


template1 = "explain data science"

# Create a multi-line string as a prompt template
# This prompt instructs the LLM to act as a programming exercise corrector
template = """
You act like a programming corrector.

You will be given in one text two things :
1. A programming exercise statement.
2. A student's code solution.

Your job is to :
- Give the answer a score from 0 to 10, and then go back to the line
- Examine the exercise
- If it is incorrect, explain the mistake(s) and provide a corrected version.

If the submitted text is something else rather than a programming exercise and an answer, you communicate normally with the student

### Exercise and student code :
{exercise_answer}

### Evaluation:
"""


# Create a PromptTemplate object
# This replaces `{exercise_answer}` dynamically when the chain is run
prompt = PromptTemplate(
    input_variables=["exercise_answer"],  # Defines that the prompt will take one input variable
    template=template,  # Assigns the actual template string
)


# Create an LLMChain that connects the model and the prompt
# When run, it sends the formatted prompt to the model and gets a response
chain = LLMChain(llm=llm, prompt=prompt)
