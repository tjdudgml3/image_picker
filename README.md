# image_picker
graduation AI project

2021 졸업 프로젝트

시연 영상- [https://github.com/tjdudgml3/image_picker/blob/main/%EC%8B%9C%EC%97%B0%EC%98%81%EC%83%81.mp4]

파이썬 모델 학습코드 =[https://github.com/tjdudgml3/image_picker/blob/main/resnet18_%ED%95%99%EC%8A%B5%EB%B6%80%EB%B6%84.ipynb]
- 모델은 RestNet18, Resnet50을 사용하고싶었지만 colab메모리가 버티지를 못했다.
-split() 데이터셋 트레인,테스트 데이터 나누는 함수
-label() 수집한 데이터셋의 폴더에 따라 레이벨링
-preprocess() - rotation,flip 등등 데이터 증강을 하고 모델에 데이터 input하기 위한 전처리함수
- 그 이후에 학습을 한 후 tensorflowlite 로 안드로이드에 사용할 수 있게 한다.

안드로이드 java(interaction)부분
main[https://github.com/tjdudgml3/image_picker/blob/main/MainActivity.java]
모델삽입[https://github.com/tjdudgml3/image_picker/blob/main/ClassifierWithSupport.java]
이미지 리사이클러뷰[https://github.com/tjdudgml3/image_picker/blob/main/Subactivity.java]

##PPT
##[https://github.com/tjdudgml3/image_picker/blob/main/%EC%8B%9C%EC%97%B0%EC%98%81%EC%83%81.mp4]
