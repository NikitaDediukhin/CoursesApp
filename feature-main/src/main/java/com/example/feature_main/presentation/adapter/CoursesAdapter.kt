package com.example.feature_main.presentation.adapter

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core_ui.R
import com.example.feature_main.databinding.ItemCourseBinding
import com.example.feature_main.domain.model.Course
import com.example.feature_main.presentation.resolveCourseImage
import com.example.feature_main.presentation.utils.formatCourseDate

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
            val context = root.context

            titleTextView.text = course.title
            descriptionTextView.text = course.text
            priceTextView.text = context.getString(
                com.example.feature_main.R.string.price_format,
                course.price
            )
            rateTextView.text = "${course.rate}"
            startDateTextView.text = formatCourseDate(course.startDate)

            courseImageView.setImageResource(resolveCourseImage(course.id))

            bindBookmarkState(course)

            likeButton.setOnClickListener {
                onFavoriteClick(course)
            }
        }

        private fun bindBookmarkState(course: Course) = with(binding.likeButton) {
            if (course.hasLike) {
                setImageResource(R.drawable.ic_bookmark_filled)
                imageTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.accent_primary)
                )
            } else {
                setImageResource(R.drawable.ic_bookmark_outlined)
                imageTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.text_primary)
                )
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