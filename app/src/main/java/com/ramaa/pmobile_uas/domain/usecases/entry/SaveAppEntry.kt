package com.ramaa.pmobile_uas.domain.usecases.entry

import com.ramaa.pmobile_uas.domain.manager.LocalUserManager
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }

}