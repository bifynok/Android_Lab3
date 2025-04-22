package com.example.lab3

import android.os.Bundle
import android.text.*
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment

class InputFragment : Fragment() {

    private var onTextEntered: ((String) -> Unit)? = null
    private var onButtonListClicked: (() -> Unit)? = null

    fun setOnTextEnteredListener(listener: (String) -> Unit) {
        onTextEntered = listener
    }

    fun setOnButtonListClickedListener(listener: () -> Unit) {
        onButtonListClicked = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.input_fragment, container, false)

        val editText = view.findViewById<EditText>(R.id.editText)

        editText.filters = arrayOf(InputFilter { source, _, _, _, _, _ ->
            if (source.contains(" ")) "" else null
        })

        val buttonOk = view.findViewById<Button>(R.id.buttonOk)
        val buttonList = view.findViewById<Button>(R.id.buttonList)
        val showPassword = view.findViewById<RadioButton>(R.id.showPassword)
        val hidePassword = view.findViewById<RadioButton>(R.id.hidePassword)

        buttonOk.setOnClickListener {
            val text = editText.text.toString()
            onTextEntered?.invoke(text)
        }

        buttonList.setOnClickListener {
            onButtonListClicked?.invoke()
        }

        showPassword.setOnClickListener {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            editText.setSelection(editText.text.length)
        }

        hidePassword.setOnClickListener {
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editText.setSelection(editText.text.length)
        }

        return view
    }
}
