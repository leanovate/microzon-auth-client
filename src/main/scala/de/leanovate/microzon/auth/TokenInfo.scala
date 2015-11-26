package de.leanovate.microzon.auth

case class TokenInfo(
                      raw: String,
                      sub: String,
                      exp: Long,
                      realm: Option[String],
                      x5t: String,
                      sha256: String
                    )
