package com.sng.sender;

import com.sng.dto.Request;
import com.sng.dto.ResultInfo;

public interface Sender {
	
	/**
	 * Enviar la peticion.
	 * @param req la peticion a enviar.
	 * @return the result info.
	 */
	ResultInfo send (Request req);

}
