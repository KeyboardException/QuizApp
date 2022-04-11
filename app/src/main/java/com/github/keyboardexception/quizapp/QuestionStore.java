package com.github.keyboardexception.quizapp;

import com.github.keyboardexception.quizapp.Objects.Category;
import com.github.keyboardexception.quizapp.Objects.Question;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class QuestionStore {
	public ArrayList<Category> categories;

	public QuestionStore() {
		categories = new ArrayList<>();

		Category fun = new Category("Mẹo", "mathematical");
		categories.add(fun);

		fun.add(new Question(
			"Có một tàu điện đi về hướng nam. Gió hướng tây bắc. Vậy khói từ con tàu sẽ theo hướng nào?",
			"Đông",
			"Tây",
			"Bắc",
			"Không hướng nào",
			4
		));

		fun.add(new Question(
			"Làm thế nào để không đụng phải ngón tay khi bạn đập búa vào một cái móng tay?",
			"Cầm búa bằng cả 2 tay",
			"Cầm búa bằng tay trái",
			"Cầm búa bằng tay phải",
			"Cầm búa bằng chân",
			1
		));

		fun.add(new Question(
			"Có anh chàng dừng xe ở trước cửa 1 Nhà nghỉ nọ, vẻ như đang chờ ai đó. Chợt có một bà bầu đi ngang qua, anh ta liền hỏi: \"Chị làm ơn coi hộ mấy giờ?\". Chị kia đáp nhanh: \"Mười tám tháng\". Anh chàng vội cảm ơn rồi lẩm bẩm \"Mình đến sớm 20phút rồi, lấy phòng lên nằm đợi vậy\""
				+ "\nBạn cho biết lúc đó đồng hồ trên tay anh kia chỉ mấy giờ?",
			"5h30'",
			"15h30'",
			"10h00'",
			"18h30'",
			2
		));

		fun.add(new Question(
			"Nếu bạn nhìn thấy con chim đang đậu trên nhánh cây, làm sao để lấy nhánh cây mà không làm động con chim?",
			"Bắt chim bỏ ra ngoài",
			"Đợi chim bay đi.",
			"Ru chim ngủ rồi lấy",
			"Cứ đến mà lấy",
			1
		));

		fun.add(new Question(
			"Miệng rộng lớn nhưng không nói một từ, là con gì?",
			"Con cá voi",
			"Con khỉ đột",
			"Con sông",
			"Con voi",
			1
		));

		fun.add(new Question(
			"Có bao nhiêu chữ C trong câu sau: \"Cơm, canh, cá, tất cả em đều thích\"?",
			"1",
			"2",
			"4",
			"5",
			1
		));

		fun.add(new Question(
			"Loại nước giải khát nào chứa Canxi và Sắt?",
			"CoCa",
			"Pepsi",
			"Cafe",
			"Sinh tố",
			3
		));

		fun.add(new Question(
			"Cái gì bạn không mượn mà trả?",
			"Tiền",
			"Lời cảm ơn",
			"Tình",
			"Không có thứ gì",
			2
		));

		fun.add(new Question(
			"Vào lúc nửa đêm đồng hồ bất chợt gõ 13 tiếng, chuyện gì xảy ra?",
			"Có ma",
			"Chuyện xấu sẽ đến",
			"Ngày tận thế",
			"Mang đồng hồ đi sửa",
			4
		));

		fun.add(new Question(
			"Cái gì luôn đi đến mà không bao giờ đến nơi?",
			"Cơn gió",
			"Tình yêu",
			"Ngày mai",
			"Không biết là cái gì",
			3
		));

		fun.add(new Question(
			"Tìm điểm sai trong câu: \"Dưới ánh nắng sương long lanh triệu cành hồng khoe sắc thắm\".",
			"Ánh nắng sương",
			"Triệu cành hồng",
			"Khoe sắc thắm",
			"Sương long lanh",
			3
		));

		fun.add(new Question(
			"8 chia 4 = ?",
			"1/4",
			"1/2",
			"3/4",
			"4/3",
			4
		));

		fun.add(new Question(
			"Một anh thanh niên đánh 1 bà già hỏi anh ấy mất gì?",
			"Mất tiền.",
			"Mất trí.",
			"Mất sức.",
			"Mất dạy.",
			4
		));

		fun.add(new Question(
			"Có 2 người cha và 2 người con cùng chia đều số tiền là 27 nghìn. Hỏi mỗi người được bao nhiều?",
			"6,75",
			"7",
			"7,5",
			"9",
			4
		));

		fun.add(new Question(
			"Có 1 đàn chuột điếc đi ngang qua hố cống, hỏi có mấy con?",
			"12",
			"18",
			"24",
			"30",
			3
		));

		fun.add(new Question(
			"Cái gì mà đi thì nằm, đứng cũng nằm, nhưng nằm lại đứng?",
			"Cái bàn",
			"Cái ghế",
			"Bàn chân",
			"Bàn tay",
			3
		));

		fun.add(new Question(
			"Một kẻ giết người bị kết án tử hình. Hắn ta phải chọn một trong ba căn phòng: phòng thứ nhất lửa cháy dữ dội, phòng thứ hai đầy những kẻ ám sát đang giương súng, và phòng thứ ba đầy sư tử nhịn đói trong ba năm. Phòng nào an toàn nhất cho hắn?",
			"Phòng thứ nhất",
			"Phòng thứ hai",
			"Phòng thứ ba",
			"Không phòng nào",
			3
		));

		fun.add(new Question(
			"Bố mẹ có 6 người con trai, mỗi người con trai có một đứa em gái. Vậy gia đình đó có mấy người?",
			"8",
			"9",
			"10",
			"11",
			2
		));

		fun.add(new Question(
			"Chồng người da đen, vợ người da trắng vừa sinh một đứa bé, răng của nó màu gì?",
			"Trắng",
			"Đen",
			"Vàng",
			"Không có màu",
			4
		));
	}

	public void deploy() {
		for (Category category : categories) {
			for (Question question : category.questions)
				question.save();

			category.save();
		}
	}
}
