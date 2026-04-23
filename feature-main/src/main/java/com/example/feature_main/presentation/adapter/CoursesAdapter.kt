package com.example.feature_main.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.feature_main.databinding.ItemCourseBinding
import com.example.feature_main.domain.model.Course
import com.example.feature_main.presentation.resolveCourseImage

class CoursesAdapter(
    private val onFavoriteClick: (Course) -> Unit
) : ListAdapter<Course, CoursesAdapter.CourseViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCourseBinding.inflate(inflater, parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CourseViewHolder(
        private val binding: ItemCourseBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) = with(binding) {
            titleTextView.text = course.title
            descriptionTextView.text = course.text
            priceTextView.text = "Цена: ${course.price}"
            rateTextView.text = "Рейтинг: ${course.rate}"
            startDateTextView.text = "Старт: ${course.startDate}"

            courseImageView.setImageResource(resolveCourseImage(course.id))

            val colorRes = if (course.hasLike) {
                android.R.color.holo_green_light
            } else {
                android.R.color.darker_gray
            }

            likeButton.setColorFilter(
                ContextCompat.getColor(binding.root.context, colorRes)
            )

            likeButton.setOnClickListener {
                onFavoriteClick(course)
            }
        }
    }

    private object DiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Course, newItem: Course): Boolean {
            return oldItem == newItem
        }
    }
}