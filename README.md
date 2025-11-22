# Enomdeul-Sever

### 🌐 Git-flow 전략 (Git-flow Strategy)

- **`main`**: 최종적으로 사용자에게 배포되는 가장 안정적인 버전 브랜치
- **`develop`**: 다음 출시 버전을 개발하는 중심 브랜치.
- **`feat-닉네임`**: 기능 개발용 개인 브랜치. `develop`에서 분기하여 작업

### 📌 브랜치 규칙 및 네이밍 (Branch Rules & Naming)

1. 모든 기능 개발은 **feature** 브랜치에서 시작
2. 작업 시작 전, 항상 최신 `develop` 내용 받아오기 (`git pull origin develop`)
3. 작업 완료 후, `develop`으로 Pull Request(PR) 생성


### 🎯 커밋 컨벤션 (Commit Convention)

- **주의 사항:**
- `type`은 소문자만 사용 (feat, fix, refactor, docs, style, test, chore)
- `subject`는 **모두 현재형 동사**

| type | subject |
| --- | --- |
| feat | 새로운 기능 추가 |
| fix | 버그 수정 |
| docs | 문서 수정 |
| style | 코드 포맷팅, 오타 수정, 주석 수정 및 삭제 등 |
| refactor | 코드 리팩토링 |
| test | 테스트 코드 |
| chore | 빌드 및 패키지 수정 및 삭제 |
| ui | UI 및 스타일 파일 추가 및 수정 |
| merge | 브랜치를 머지 |

```bash
feat: 리뷰 작성 api 추가
fix: 로그인 오류 해결
```
