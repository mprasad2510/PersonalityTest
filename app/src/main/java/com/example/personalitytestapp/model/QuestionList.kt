package com.example.personalitytestapp.model

import com.google.gson.annotations.SerializedName

data class QuestionList(

	@field:SerializedName("data")
	val data: Data? = null
)

data class QuestionType(

	@field:SerializedName("options")
	val options: List<String?>? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("condition")
	val condition: Condition? = null,

	@field:SerializedName("range")
	val range: Range? = null
)

data class Range(

	@field:SerializedName("from")
	val from: Int? = null,

	@field:SerializedName("to")
	val to: Int? = null
)

data class Predicate(

	@field:SerializedName("exactEquals")
	val exactEquals: List<String?>? = null
)

data class QuestionsItem(

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("question_type")
	val questionType: QuestionType? = null,

	@field:SerializedName("category")
	val category: String? = null
)

data class Data(

	@field:SerializedName("questions")
	val questions: List<QuestionsItem?>? = null,

	@field:SerializedName("categories")
	val categories: List<String?>? = null
)

data class Condition(

	@field:SerializedName("predicate")
	val predicate: Predicate? = null,

	@field:SerializedName("if_positive")
	val ifPositive: IfPositive? = null
)

data class IfPositive(

	@field:SerializedName("question")
	val question: String? = null,

	@field:SerializedName("question_type")
	val questionType: QuestionType? = null,

	@field:SerializedName("category")
	val category: String? = null
)
