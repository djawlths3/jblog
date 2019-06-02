function checkId() {
	let inputId = $('#id').val();
	$('#img-checkemail').css('display','none')
	$('#btn-checkemail').css('display','')
	if (inputId.length < 2 || inputId.length > 10) {
		$('#idMsg').text('아이디 길이는 2~10 사이입니다')
		$('#submitBtn').attr('disabled', true)
		return false
	} else {
		$('#idMsg').text('')
		$('#submitBtn').attr('disabled', true)
		return true
	}
}

function checkPw() {
	let pwId = $('#pw').val();
	if (pwId == '') {
		$('#pwMsg').text('비밀번호를 입력해주세요')
		// $('#submitBtn').attr('disabled', true);
		return false
	} else {
		$('#pwMsg').text('')
		return true
	}
}

function checkName() {
	let inputName = $('#name').val();
	if (inputName == '') {
		$('#nameMsg').text('빈칸을 채워주세요')
		// $('#submitBtn').attr('disabled', true);
		return false
	} else {
		$('#nameMsg').text('')
		return true
	}
}