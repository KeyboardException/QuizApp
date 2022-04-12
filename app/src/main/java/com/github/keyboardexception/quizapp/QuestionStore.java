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

		Category Lop5 = new Category("Ai Thông Minh Hơn Học Sinh Lớp 5", "mathematical");
		categories.add(Lop5);

		Lop5.add(new Question(
				"Địa - Đỉnh núi cao nhất thế giới thuộc châu lục nào?",
				"Châu Á",
				"Châu Âu",
				"Châu Phi",
				"Châu Mỹ",
				1
		));

		Lop5.add(new Question(
				"Tiếng Việt - Trong bài \"Chuỗi ngọc lam\" (Tiếng Việt lớp 5 - tập I) cô bé mua chuỗi ngọc lam để tặng ai?",
				"Tặng Mẹ",
				"Tặng Chị",
				"Tặng Ba",
				"Tặng Bạn",
				2
		));

		Lop5.add(new Question(
				"Sử - Cuộc phản công ở kinh thành Huế đã gắn liền với tên tuổi vị vua nào?",
				"Vua Hàm Nghi",
				"Vua Duy Tân",
				"Vua Tự Đức",
				"Vua Gia Long",
				1
		));

		Lop5.add(new Question(
				"Toán - Rút gọn phân số 35/65 được kết quả là:",
				"1/2",
				"5/13",
				"7/13",
				"1/3",
				3
		));

		Lop5.add(new Question(
				"Tiếng Việt - Trong câu: \"Dòng suối róc rách như pha lê, hát lên những bản nhạc dịu dàng\", tác giả đã sử dụng biện pháp nghệ thuật nào?",
				"So Sánh",
				"So Sánh và Nhân Hóa",
				"Nhân Hóa",
				"Không Sử Dụng",
				2
		));

		Lop5.add(new Question(
				"Khoa - Bệnh viêm gan A lây truyền qua đường nào?",
				"Đường hô hấp",
				"Dường máu",
				"Đường tiêu hóa",
				"Tuyến mồ hôi",
				3
		));

		Lop5.add(new Question(
				"Tiếng Việt - Xác định từ loại của từ được gạch chân trong câu văn sau:\n" +
						"\n" +
						"\"Dù ông ta có một đống của nhưng ông ta không thấy hạnh phúc.\"",
				"Tính Từ",
				"Động Từ",
				"Trạng Từ",
				"Danh Từ",
				4
		));

		Lop5.add(new Question(
				"Toán - Cạnh của một hình lập phương gấp lên 3 lần thì thể tích của hình lập phương đó gấp lên mấy lần?",
				"20 lần",
				"3 lần",
				"9 lần",
				"27 lần",
				4
		));

		Lop5.add(new Question(
				"Tiếng Việt - Thành ngữ nào dưới đây không nói về vẻ đẹp thiên nhiên:",
				"Non xanh nước biếc",
				"Giang sơn gấm vóc",
				"Sớm nắng chiều mưa",
				"Cần cù bù thông minh",
				3
		));

		Lop5.add(new Question(
				"Tiếng Việt - Hoàn thành thành ngữ sau: \"Cần cù bù ....\"",
				"Thông Minh",
				"Siêng Năng",
				"Mệt Mỏi",
				"Thì Mới Có Ăn",
				1
		));

		Lop5.add(new Question(
				"Tiếng Anh - What was the matter with him? He........ a toothache",
				"to have",
				"have",
				"has",
				"had",
				4
		));

		Lop5.add(new Question(
				"Tiếng Anh - What subject is he......... now? Vietnamese.",
				" to learn",
				" learn",
				"learning",
				"learned",
				3
		));

		Lop5.add(new Question(
				"Tiếng Anh - ............ did she go yesterday morning? She went to the bookshop",
				"What",
				"Where",
				"When",
				"Why",
				1
		));

		Lop5.add(new Question(
				"Are you free........ the evening? Yes, I am.",
				"in",
				"on",
				"at",
				"to",
				1
		));

		Category Toanlop5 = new Category("Toán 5 - Nâng Cao", "mathematical");
		categories.add(Toanlop5);

		Toanlop5.add(new Question(
				"Bạn Toàn nhân một số với 2002 nhưng “đãng trí” quên viết 2 chữ số 0 của số 2002 nên kết quả “bị” giảm đi 3965940 đơn vị. Toàn đã định nhân số nào với 2002?",
				"1980",
				"2022",
				"3965940",
				"2003",
				4
		));

		Toanlop5.add(new Question(
				"Người ta cộng 5 số và chia cho 5 thì được 138. Nếu xếp các số theo thứ tự lớn dần thì cộng 3 số đầu tiên và chia cho 3 sẽ được 127, cộng 3 số cuối và chia cho 3 sẽ được 148. Bạn có biết số đứng giữa theo thứ tự trên là số nào không?",
				"690",
				"381",
				"444",
				"135",
				4
		));

		Toanlop5.add(new Question(
				"Cho bảng ô vuông gồm 10 dòng và 10 cột. Hai bạn Tín và Nhi tô màu các ô, mỗi ô một màu trong 3 màu: xanh, đỏ, tím. Bạn Tín bảo: \"Lần nào tô xong hết các ô cũng có 2 dòng mà trên 2 dòng đó có một màu tô số ô dòng này bằng tô số ô dòng kia\". Bạn Nhi bảo: \"Tớ phát hiện ra bao giờ cũng có 2 cột được tô như thế\".\n" +
						"Nào, bạn hãy cho biết ai đúng, ai sai?",
				"Tín đúng",
				"Nhi đúng",
				"Cả hai bạn đều đúng",
				"Cả hai bạn đều sai",
				3
		));

		Toanlop5.add(new Question(
				"Một người mang ra chợ 5 giỏ táo gồm hai loại. Số táo trong mỗi giỏ lần lượt là: 20 ; 25 ; 30 ; 35 và 40. Mỗi giỏ chỉ đựng một loại táo. Sau khi bán hết một giỏ táo nào đó, người ấy thấy rằng : Số táo loại 2 còn lại đúng bằng nửa số táo loại 1. Hỏi số táo loại 2 còn lại là bao nhiêu?",
				"150 quả",
				"40 quả",
				"30 quả",
				"120 quả",
				2
		));

		Toanlop5.add(new Question(
				"S = 1/2 + 1/3 + 1/4 + 1/5 + 1/6 + 1/7 + 1/8 có phải là số gì?",
				"S không phải số tự nhiên",
				"S là số tự nhiên",
				"S là số lẻ",
				"s không là số lẻ",
				1
		));

		Toanlop5.add(new Question(
				"Số có 1995 chữ số 7 khi chia cho 15 thì phần thập phân của thương là bao nhiêu?",
				"8",
				"3",
				"7",
				"4",
				1
		));

		Toanlop5.add(new Question(
				"Tuổi ông hơn tuổi cháu là 66 năm. Biết rằng tuổi ông bao nhiêu năm thì tuổi cháu bấy nhiêu tháng. Hãy tính tổng tuổi ông và tuổi cháu?",
				"72",
				"64",
				"86",
				"78",
				4
		));

		Toanlop5.add(new Question(
				"Một vị phụ huynh học sinh hỏi thầy giáo: \"Thưa thầy, trong lớp có bao nhiêu học sinh?\" Thầy cười và trả lời:\"Nếu có thêm một số trẻ em bằng số hiện có và thêm một nửa số đó, rồi lại thêm 1/4 số đó, rồi cả thêm con của quý vị (một lần nữa) thì sẽ vừa tròn 100\". Hỏi lớp có bao nhiêu học sinh?",
				"99",
				"36",
				"54",
				"62",
				2
		));

		Toanlop5.add(new Question(
				"Tham gia hội khoẻ Phù Đổng huyện có tất cả 222 cầu thủ thi đấu hai môn: Bóng đá và bóng chuyền. Mỗi đội bóng đá có 11 người. Mỗi đội bóng chuyền có 6 người. Biết rằng có cả thảy 27 đội bóng, hãy tính tổng số đội bóng đá và số đội bóng chuyền.",
				"20",
				"34",
				"27",
				"56",
				3
		));

		Toanlop5.add(new Question(
				"Tìm một số tự nhiên sao cho khi lấy 1/3 số đó chia cho 1/17 số đó thì có dư là 100",
				"2550",
				"2500",
				"2600",
				"2650",
				1
		));

		Category Anhlop5 = new Category("Tiếng Anh 5 - Nâng Cao", "mathematical");
		categories.add(Anhlop5);

		Anhlop5.add(new Question(
				"I have worked for this company ………2019",
				"in",
				"on",
				"since",
				"for",
				3
		));

		Anhlop5.add(new Question(
				"She has taught English……. 2 years",
				"in",
				"on",
				"since",
				"for",
				4
		));

		Anhlop5.add(new Question(
				"He hasn’t ………..her since she was a little girl",
				"meet",
				"met",
				"meeted",
				"meets",
				2
		));

		Anhlop5.add(new Question(
				"I have …….English since I was a child.",
				"study",
				"studies",
				"studyed",
				"studied",
				4
		));

		Anhlop5.add(new Question(
				"They …….cancelled the meeting.",
				"do",
				"are",
				"were",
				"have",
				4
		));

		Anhlop5.add(new Question(
				" Water ……….at 100 degrees.",
				"boil",
				"boils",
				"boiled",
				"is boiles",
				2
		));

		Anhlop5.add(new Question(
				"The sun ………..in the east.",
				"rise",
				"rises",
				"rised",
				"is rised",
				2
		));

		Anhlop5.add(new Question(
				"I like ……….. ice cream so much",
				"eating",
				"eat",
				"ate",
				"eated",
				1
		));

		Anhlop5.add(new Question(
				"How many…….. are there",
				"people",
				"water",
				"rice",
				"money",
				1
		));

		Anhlop5.add(new Question(
				"The meeting starts ……… 9 am",
				"on",
				"in",
				"at",
				"of",
				3
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
