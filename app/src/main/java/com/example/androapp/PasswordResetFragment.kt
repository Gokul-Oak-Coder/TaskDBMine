package com.nttdata.androapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.blogspot.atifsoftwares.animatoolib.Animatoo
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_password_reset.*


class PasswordResetFragment : Fragment() {

    lateinit var textInputLayout8: TextInputLayout
    lateinit var phoneNumber: TextInputEditText
    lateinit var sendOTP : AppCompatButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_password_reset, container, false)

        textInputLayout8 = view.findViewById(R.id.textInputLayout8)
        phoneNumber = view.findViewById(R.id.phoneNumber)
        sendOTP = view.findViewById(R.id.otp_btn)

        sendOTP.setOnClickListener {
            if(phoneNumber.text.toString().isEmpty()){
                textInputLayout8.error = "Please enter registered phone number"
            }
           else if (phoneNumber.text.toString().length == 10 ){
               textInputLayout8.error = "Please check your phone number"
           }
           else{
            context?.let { it1 -> Animatoo.animateSwipeRight(it1) }
                textView13.isVisible = true
                otp_box1.isVisible = true
                otp_box2.isVisible = true
                otp_box3.isVisible = true
                otp_box4.isVisible = true
                reset_password_layout.isVisible = true
                conirm_resetPassword_layout.isVisible = true
                reset_password_btn.isVisible = true
            Toast.makeText(context, "OTP sent to your mobile", Toast.LENGTH_SHORT).show()
        }
        }
        phoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textInputLayout8.error = null
            }
            override fun afterTextChanged(p0: Editable?) {

            }
        })
        return view
    }


}