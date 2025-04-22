package com.example.lab3

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.io.File

class OutputFragment : Fragment(R.layout.output_fragment) {
    private lateinit var outputList: LinearLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        outputList = view.findViewById(R.id.outputList)
    }

    fun showList(list: MutableList<String>) {
        outputList.removeAllViews()

        for ((index, item) in list.withIndex()) {
            val rowLayout = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }

            val textView = TextView(requireContext()).apply {
                layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                text = item
                setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
                setTextColor(ContextCompat.getColor(context, android.R.color.black))
            }

            val deleteButton = Button(requireContext()).apply {
                text = "✖"
                setOnClickListener {
                    list.removeAt(index)
                    rewriteFile(list)
                    showList(list)
                }
            }

            rowLayout.addView(textView)
            rowLayout.addView(deleteButton)
            outputList.addView(rowLayout)
        }
    }

    fun hideList() {
        outputList.removeAllViews()
    }

    private fun rewriteFile(list: List<String>){
        val file = File(requireActivity().filesDir, "passwords.txt")
        file.writeText("")

        for (text in list) {
            file.appendText("$text\n")
        }
    }
}
