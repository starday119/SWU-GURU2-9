package om.androidbook.medicine4

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import om.androidbook.medicine4.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    lateinit var signupBinding: ActivitySignupBinding
    var DB: DBHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signupBinding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(signupBinding.root)
        DB = DBHelper(this, "DRUG_INFO", null, 3)


        signupBinding.joinButton.setOnClickListener {
            val name = signupBinding.inputname.text.toString()
            val email = signupBinding.inputEM.text.toString()
            val password = signupBinding.inputPW.text.toString()
            val birth = signupBinding.inputBR.text.toString()
            val phonenumber = signupBinding.inputPN.text.toString()

            if (name == "" || email == "" || password == "" || birth == "" || phonenumber == "") {
                Toast.makeText(
                    this@SignupActivity,
                    "회원정보를 전부 입력해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val checkEM = DB!!.checkEM(email)
                if (!checkEM) {
                    val insert = DB!!.insertData(name, email, password, birth, phonenumber)
                    if (insert) {
                        Toast.makeText(
                            this@SignupActivity,
                            "가입되었습니다",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@SignupActivity, LoginActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@SignupActivity,
                            "가입에 실패했습니다",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@SignupActivity,
                        "이미 가입된 회원입니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

//
}



