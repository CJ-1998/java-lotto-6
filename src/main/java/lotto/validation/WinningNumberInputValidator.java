package lotto.validation;

import lotto.domain.Lotto;
import lotto.domain.WinningNumber;

public class WinningNumberInputValidator {

    public boolean isCommaFive(String winningNumberInput) {
        int commaCount = 0;
        for (int i = 0; i < winningNumberInput.length(); i++) {
            char oneCharacter = winningNumberInput.charAt(i);
            if (oneCharacter == ',') {
                commaCount++;
            }
        }
        return commaCount == 5;
    }

    public boolean isLottoNumber(int oneLottoNumber) {
        return (1 <= oneLottoNumber && oneLottoNumber <= 45);
    }

    public boolean isLottoNumberDuplication(WinningNumber winningNumber) {
        Lotto winningLotto = winningNumber.getWinningNumber();
        int bonusNumber = winningNumber.getBonusNumber();

        for (int i = 0; i < winningLotto.size(); i++) {
            int oneNumber = winningLotto.get(i);
            if (winningLotto.contains(oneNumber)) {
                return false;
            }
        }

        if (winningLotto.contains(bonusNumber)) {
            return false;
        }
        return true;
    }
}
