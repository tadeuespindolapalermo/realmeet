package br.com.sw2you.realmeet.report.validator;

import br.com.sw2you.realmeet.report.model.AbstractReportData;
import br.com.sw2you.realmeet.validator.ValidationErrors;

import static br.com.sw2you.realmeet.validator.ValidatorConstants.EMAIL;
import static br.com.sw2you.realmeet.validator.ValidatorUtils.throwOnError;
import static br.com.sw2you.realmeet.validator.ValidatorUtils.validateRequired;

public abstract class AbstractReportValidator {

    public void validate(AbstractReportData reportData) {
        var validationErrors = new ValidationErrors();

        validateRequired(reportData.getEmail(), EMAIL, validationErrors);
        validate(reportData, validationErrors);

        throwOnError(validationErrors);
    }

    protected abstract void validate(AbstractReportData reportData, ValidationErrors validationErrors);
}
