/**
 * Copyright © 2019 inkbox (enpassPixiv@protonmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.inkroom.log.client.log4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 墨盒
 * @Date 19-6-4
 */
public class Entry {

    private static Logger logger = LoggerFactory.getLogger(Entry.class);

    public static void main(String[] args) {
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                logger.info(args[i]);
            }
        }
        System.exit(0);
    }
}
