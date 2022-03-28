# feedback
Feedback uses NLP techniques to read and process the data of the SRA dataset as an ecxel file. Our dataset is short answers (in excel file format) with assessments and teacher feedbacks. In the current part of the project, the data is statistically analyzed:
- Number of words and sentences in each feedback, learner answer, answer and question.
- Type-Token Ratio
- Rate content words
- How many words (and content words) of feedback are in and are not in questions, learner answers, and target answers.
# Usage
## First Steps
- Import Feedback as a maven project in eclipse
- Adjust the paths to the data to be read (in baseExperiment class), and where the data should be output and try running BaseExperiment class
## DKPro Pipeline
When running java program, a DKPro Pipeline will be executed:
- The SRAFeedbackLineReader class is first called to read the dataset. Here, informations such as questions, answers, target answers, and feedbacks, number of feedback will be read and saved to the SRAItem.
- One jcas for each response message will be generated.
- Next class Analyzer will be called. Here statistical calculations will be performed and exported. For example: number of words, number of sentences, type token ratio, number of words that overlap and not overlap
