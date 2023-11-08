package lotto.controller;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;
import lotto.Exception.LottoException;
import lotto.domain.Lotto;
import lotto.domain.WinningNumber;
import lotto.validation.InputValidator;
import lotto.validation.WinningNumberInputValidator;

public class WinningNumberInputController {

    InputValidator inputValidator = new InputValidator();
    WinningNumberInputValidator winningNumberInputValidator = new WinningNumberInputValidator();
    LottoException lottoException = new LottoException();

    public WinningNumber inputWinningLotto() {
        Lotto lotto = inputWinningNumber();
        int bonusNumber = inputBonusNumber(lotto);

        return new WinningNumber(lotto, bonusNumber);
    }

    public Lotto inputWinningNumber() {
        String winningNumber = "";
        Lotto lotto;
        while (true) {
            winningNumber = Console.readLine();
            try {
                checkWinningNumber(winningNumber);
                lotto = splitWinningNumber(winningNumber);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                continue;
            }
        }
        return lotto;
    }

    public void checkWinningNumber(String winningNumber) throws IllegalArgumentException {
        if (!winningNumberInputValidator.isCommaFive(winningNumber)) {
            lottoException.notSixLottoNumber();
        }
    }

    public Lotto splitWinningNumber(String winningNumber) throws IllegalArgumentException {
        String[] number = winningNumber.split(",");
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < number.length; i++) {
            int oneNumber = Integer.parseInt(number[i]);
            if (!winningNumberInputValidator.isLottoNumber(oneNumber)) {
                lottoException.notLottoNumber();
            }
            numbers.add(oneNumber);
        }
        return new Lotto(numbers);
    }

    public int inputBonusNumber(Lotto winningLotto) {
        String bonusNumber = "";
        while (true) {
            try {
                bonusNumber = Console.readLine();
                checkBonusNumber(bonusNumber);
                checkBonusNumberDuplication(winningLotto, bonusNumber);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                continue;
            }
        }
        return Integer.parseInt(bonusNumber);
    }

    public void checkBonusNumber(String bonusNumber) throws IllegalArgumentException {
        if (!inputValidator.isNumber(bonusNumber)) {
            lottoException.notNumber();
        }
        checkBonusNumberRange(bonusNumber);
    }

    public void checkBonusNumberRange(String bonusNumber) throws IllegalArgumentException {
        int realBonusNumber = Integer.parseInt(bonusNumber);
        if (!winningNumberInputValidator.isLottoNumber(realBonusNumber)) {
            lottoException.notLottoNumber();
        }
    }

    public void checkBonusNumberDuplication(Lotto winningLotto, String bonusNumber) throws IllegalArgumentException {
        int realBonusNumber = Integer.parseInt(bonusNumber);
        WinningNumber winningNumber = new WinningNumber(winningLotto, realBonusNumber);
        if (!winningNumberInputValidator.isWinningLottoNotDuplication(winningNumber)) {
            lottoException.duplicationLottoNumber();
        }
    }
}
